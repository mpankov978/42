import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_team_project/screens/chats_screens/chat_buddy_screen.dart';

class ChatScreen extends StatefulWidget {
  const ChatScreen({Key key}) : super(key: key);

  @override
  State<ChatScreen> createState() => _ChatScreenState();
}

class _ChatScreenState extends State<ChatScreen> {

  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          const Padding(
            padding: EdgeInsets.only(left: 20, right: 20, top: 30, bottom: 20),
            child: Text(
              'Чаты',
              style: TextStyle(color: Colors.black, fontSize: 36),
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(
              bottom: 5,
              left: 5,
            ),
            child: ListTile(
                leading: SvgPicture.asset('assets/chat_1.svg'),
                title: const Text(
                  'Уведомление',
                  style: TextStyle(color: Colors.black, fontSize: 21),
                ),
                subtitle: const Text(
                  'Какое-то очень важное уведомление',
                  style: TextStyle(
                    color: Colors.grey,
                  ),
                )),
          ),
          Padding(
            padding: const EdgeInsets.only(
              bottom: 5,
              left: 5,
            ),
            child: ListTile(
                onTap: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (_) => const ChatBuddyScreen()));
                },
                leading: SvgPicture.asset('assets/chat_2.svg'),
                title: const Text(
                  'Чат с бадди',
                  style: TextStyle(color: Colors.lightBlue, fontSize: 21),
                ),
                subtitle: const Text(
                  'Возможно ты хочешь поговорить н...',
                  style: TextStyle(
                    color: Colors.grey,
                  ),
                )),
          ),
        ]),
      ),
    );
  }
}
