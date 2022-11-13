import 'package:flutter/material.dart';
import 'package:flutter_team_project/config/router.dart';
import 'package:flutter_team_project/shared_preferences/shared_preferences.dart';

class WrapperScreen extends StatefulWidget {
  const WrapperScreen({Key key}) : super(key: key);

  @override
  State<WrapperScreen> createState() => _WrapperScreenState();
}

class _WrapperScreenState extends State<WrapperScreen> {
  final prefs = SharedPreferenceStorage();
  @override
  void initState() {
    prefs.getWelcome().then((value) {
      if (value == true) {
        Navigator.pushReplacementNamed(context, RouteGenerator.MAIN);
      }
      if (value = false || value == null) {
        Navigator.pushReplacementNamed(context, RouteGenerator.AUTH);
      }
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container();
  }
}
