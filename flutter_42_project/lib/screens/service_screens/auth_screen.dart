import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_team_project/api/open_user_api.dart';
import 'package:flutter_team_project/config/constants.dart';
import 'package:flutter_team_project/config/router.dart';
import 'package:flutter_team_project/shared_preferences/shared_preferences.dart';

class AuthScreen extends StatefulWidget {
  const AuthScreen({Key key}) : super(key: key);

  @override
  State<AuthScreen> createState() => _AuthScreenState();
}

class _AuthScreenState extends State<AuthScreen> {
  TextEditingController loginController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  bool isLoading = false;

  final prefs = SharedPreferenceStorage();

  @override
  void dispose() {
    loginController.dispose();
    passwordController.dispose();
    debugPrint('dispose is on');
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: isLoading
            ? const Center(
                child: CircularProgressIndicator(),
              )
            : GestureDetector(
                onTap: () {
                  unfocus(context);
                },
                child: WillPopScope(
                  onWillPop: () async => false,
                  child: SafeArea(
                    child: SingleChildScrollView(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Padding(
                            padding: const EdgeInsets.only(
                                bottom: 70, left: 20, right: 20, top: 30),
                            child: SvgPicture.asset(
                              'assets/logo.svg',
                              width: MediaQuery.of(context).size.width - 150,
                              fit: BoxFit.cover,
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(
                                left: 20, right: 20, bottom: 30),
                            child: SvgPicture.asset(
                              'assets/group.svg',
                              fit: BoxFit.cover,
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(
                                left: 40, right: 40, bottom: 20),
                            child: TextFormField(
                              controller: loginController,
                              decoration: InputDecoration(
                                  label: const Text(
                                    'Логин',
                                    style: TextStyle(color: Colors.black),
                                  ),
                                  enabledBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(15),
                                      borderSide:
                                          const BorderSide(color: Colors.blue)),
                                  focusedBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(15),
                                      borderSide: const BorderSide(
                                          color: Colors.blue))),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(
                                left: 40, right: 40, bottom: 20),
                            child: TextFormField(
                              controller: passwordController,
                              decoration: InputDecoration(
                                  label: const Text(
                                    'Пароль',
                                    style: TextStyle(color: Colors.black),
                                  ),
                                  enabledBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(15),
                                      borderSide:
                                          const BorderSide(color: Colors.blue)),
                                  focusedBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(15),
                                      borderSide: const BorderSide(
                                          color: Colors.blue))),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                              children: [
                                ClipRRect(
                                  borderRadius: BorderRadius.circular(15),
                                  child: ElevatedButton(
                                    style: ElevatedButton.styleFrom(
                                      primary: Colors.blue,
                                      padding: const EdgeInsets.symmetric(
                                          horizontal: 20, vertical: 10),
                                    ),
                                    onPressed: () async {
                                      setState(() {
                                        isLoading = true;
                                      });
                                      prefs.setWelcome(true);
                                      await OpenUserApi()
                                          .authenticate()
                                          .then((_) {
                                        Navigator.pushNamed(
                                            context, RouteGenerator.MAIN);
                                        setState(() {
                                          isLoading = false;
                                        });
                                      });
                                    },
                                    child: const Text('Войти'),
                                  ),
                                ),
                                ClipRRect(
                                  borderRadius: BorderRadius.circular(15),
                                  child: ElevatedButton(
                                    style: ElevatedButton.styleFrom(
                                      primary: Colors.white,
                                      padding: const EdgeInsets.symmetric(
                                          horizontal: 20, vertical: 10),
                                    ),
                                    onPressed: () {},
                                    child: const Text(
                                      'Зарегистрироваться',
                                      style: TextStyle(color: Colors.blue),
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          ),
                          const Padding(
                            padding: EdgeInsets.only(
                                left: 50, right: 50, bottom: 20, top: 25),
                            child: Text(
                              'Мы сделали инвестиции по-настоящему простыми и удобными. Откройте счёт бесплатно. Выбирайте комфортный тариф. Пользуйтесь лучшими технологиями и торгуйте ценными бумагами. Легко',
                              textAlign: TextAlign.center,
                            ),
                          )
                        ],
                      ),
                    ),
                  ),
                ),
              ));
  }
}
