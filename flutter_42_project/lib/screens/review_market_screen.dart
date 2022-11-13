import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/key.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_svg/flutter_svg.dart';

class ReviewMarketScreen extends StatefulWidget {
  const ReviewMarketScreen({Key key}) : super(key: key);

  @override
  State<ReviewMarketScreen> createState() => _ReviewMarketScreenState();
}

class _ReviewMarketScreenState extends State<ReviewMarketScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          automaticallyImplyLeading: false,
          elevation: 0,
          backgroundColor: Colors.white,
          title: const Text(
            'Все счета',
            style: TextStyle(color: Colors.black, fontSize: 20),
          ),
          actions: [
            IconButton(
              onPressed: () {},
              icon: const Icon(
                Icons.person,
                size: 27,
                color: Colors.grey,
              ),
            ),
            IconButton(
              onPressed: () {},
              icon: const Icon(
                Icons.notifications_none_sharp,
                color: Colors.grey,
                size: 27,
              ),
            )
          ],
        ),
        body: ListView(
          shrinkWrap: true,
          children: [
            // ignore: prefer_const_constructors
            Padding(
              padding: const EdgeInsets.only(
                  left: 30, right: 30, top: 20, bottom: 20),
              child: const Text(
                '123 456,78',
                style: TextStyle(fontSize: 34, color: Colors.black),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 30, right: 150),
              child: Card(
                elevation: 0.3,
                color: Colors.white,
                child: Padding(
                  padding: const EdgeInsets.all(6.0),
                  child: RichText(
                    text: const TextSpan(
                      text: '+7 654,32 (3,25%)',
                      style: TextStyle(
                          fontWeight: FontWeight.w300,
                          color: Colors.green,
                          fontSize: 16),
                      children: <TextSpan>[
                        TextSpan(
                            text: ' за сегодня',
                            style: TextStyle(
                                fontWeight: FontWeight.w400,
                                color: Colors.blue))
                      ],
                    ),
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(
                  left: 30, right: 30, top: 15, bottom: 15),
              child: Row(
                children: const [
                  IconActionReviewWidget(
                    icon: Icons.add,
                    text: 'Пополнить',
                  ),
                  IconActionReviewWidget(
                    icon: Icons.history,
                    text: 'История',
                  ),
                  IconActionReviewWidget(
                    icon: Icons.swap_horiz_outlined,
                    text: 'Перевести',
                  ),
                  IconActionReviewWidget(
                    icon: Icons.wallet,
                    text: 'Вывести',
                  ),
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 30, right: 30, bottom: 10),
              child: SizedBox(
                  height: 90,
                  child: ListView(
                    physics: const PageScrollPhysics(
                        parent: BouncingScrollPhysics()),
                    scrollDirection: Axis.horizontal,
                    children: const [
                      ContainerGreyWidget(),
                      SizedBox(
                        width: 10,
                      ),
                      ContainerGreyWidget(),
                      SizedBox(
                        width: 10,
                      ),
                      ContainerGreyWidget(),
                      SizedBox(
                        width: 10,
                      ),
                      ContainerGreyWidget(),
                    ],
                  )),
            ),
            const AdsPointsWidget(),
            const Padding(
              padding: EdgeInsets.only(left: 30, top: 15),
              child: Text(
                'Валюты',
                style: TextStyle(color: Colors.black, fontSize: 17),
              ),
            ),
            const RusCurrenncyWidget(),
            const RusCurrenncyWidget(),
            const RusCurrenncyWidget(),
          ],
        ));
  }
}

class RusCurrenncyWidget extends StatelessWidget {
  const RusCurrenncyWidget({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(left: 15, top: 10, right: 15),
      child: ListTile(
        leading: SvgPicture.asset(
          'assets/rus_flag.svg',
          fit: BoxFit.cover,
        ),
        title: const Text(
          'Российский рубль',
          style: TextStyle(color: Colors.black, fontSize: 16),
        ),
        trailing: const Text(
          '250,75 ₽',
          style: TextStyle(color: Colors.black, fontSize: 16),
        ),
      ),
    );
  }
}

class ContainerGreyWidget extends StatelessWidget {
  const ContainerGreyWidget({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          color: Colors.grey, borderRadius: BorderRadius.circular(15)),
      width: 90,
    );
  }
}

class AdsPointsWidget extends StatelessWidget {
  const AdsPointsWidget({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(left: 30, right: 30, top: 10, bottom: 10),
      child: Card(
        elevation: 0.6,
        child: Container(
          height: 50,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(15),
            color: Colors.blue,
          ),
          child: Padding(
            padding: const EdgeInsets.only(
              left: 10,
              right: 10,
            ),
            child: Row(children: [
              const Expanded(
                child: Text(
                  'Дарим до 2500 баллов вам и вашим друзьям',
                  style: TextStyle(color: Colors.white, fontSize: 15),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(bottom: 20),
                child: IconButton(
                    onPressed: () {},
                    icon: const Icon(
                      Icons.close,
                      color: Colors.white,
                      size: 20,
                    )),
              )
            ]),
          ),
        ),
      ),
    );
  }
}

class IconActionReviewWidget extends StatelessWidget {
  final IconData icon;
  final String text;
  const IconActionReviewWidget({this.icon, Key key, this.text})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Column(
        children: [
          Card(
            elevation: 0.4,
            child: Container(
              decoration: BoxDecoration(
                  color: Colors.white, borderRadius: BorderRadius.circular(15)),
              child: Center(
                child: GestureDetector(
                  onTap: () {
                    print('tapped');
                  },
                  child: Padding(
                    padding: const EdgeInsets.all(20.0),
                    child: Icon(
                      icon,
                      color: Colors.blue,
                      size: 30,
                    ),
                  ),
                ),
              ),
            ),
          ),
          Text(
            text,
            style: const TextStyle(color: Colors.blue, fontSize: 13),
          )
        ],
      ),
    );
  }
}
