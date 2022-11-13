import 'package:flutter/material.dart';
import 'package:flutter_team_project/screens/service_screens/auth_screen.dart';
import 'package:flutter_team_project/screens/service_screens/tab_controller_screen.dart';

class RouteGenerator {
  static const MAIN = 'main';
  static const AUTH = 'auth';

  static Route<dynamic> generateRoute(RouteSettings settings) {
    final arg = settings.arguments;
    switch (settings.name) {
      case MAIN:
        return MaterialPageRoute(builder: (_) => const TabControllerScreen());

      case AUTH:
        return MaterialPageRoute(builder: (_) => const AuthScreen());
    }
    return MaterialPageRoute(builder: (_) => const TabControllerScreen());
  }
}
