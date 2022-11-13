import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_team_project/screens/catalog_screen.dart';
import 'package:flutter_team_project/screens/chats_screens/chat_screen.dart';
import 'package:flutter_team_project/screens/lenta_screen.dart';
import 'package:flutter_team_project/screens/more_options_screen.dart';
import 'package:flutter_team_project/screens/review_market_screen.dart';

class TabControllerScreen extends StatefulWidget {
  const TabControllerScreen({Key key}) : super(key: key);

  @override
  _TabControllerScreenState createState() => _TabControllerScreenState();
}

class _TabControllerScreenState extends State<TabControllerScreen> {
  int currentIndex = 0;
  List<Widget> body = [
    const ReviewMarketScreen(),
    const CatalogScreen(),
    const LentaScreen(),
    const ChatScreen(),
    const MoreOptionScreen(),
  ];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: body[currentIndex],
      ),
      bottomNavigationBar: BottomNavigationBar(
        showUnselectedLabels: true,
        selectedItemColor: Colors.blue,
        selectedIconTheme: const IconThemeData(color: Colors.blue),
        selectedLabelStyle: const TextStyle(color: Colors.blue),
        unselectedItemColor: Colors.grey,
        unselectedLabelStyle: const TextStyle(
          color: Colors.grey,
        ),
        backgroundColor: Colors.white,
        items: [
          BottomNavigationBarItem(
              icon: SvgPicture.asset(
                'assets/home.svg',
                color: currentIndex == 0 ? Colors.blue : Colors.grey,
              ),
              label: 'Обзор'),
          BottomNavigationBarItem(
              icon: SvgPicture.asset(
                'assets/catalog.svg',
                color: currentIndex == 1 ? Colors.blue : Colors.grey,
              ),
              label: 'Каталог'),
          BottomNavigationBarItem(
              icon: SvgPicture.asset(
                'assets/pulse.svg',
                color: currentIndex == 2 ? Colors.blue : Colors.grey,
              ),
              label: 'Лента'),
          BottomNavigationBarItem(
              icon: SvgPicture.asset(
                'assets/chat.svg',
                color: currentIndex == 3 ? Colors.blue : Colors.grey,
              ),
              label: 'Чаты'),
          const BottomNavigationBarItem(
              icon: Icon(
                Icons.menu,
                size: 30,
              ),
              label: 'Еще'),
        ],
        currentIndex: currentIndex,
        onTap: (int newIndex) {
          setState(() {
            currentIndex = newIndex;
          });
        },
      ),
    );
  }
}
