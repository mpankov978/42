import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/src/foundation/key.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_team_project/api/open_chat_api.dart';
import 'package:flutter_team_project/api/open_user_api.dart';
import 'package:flutter_team_project/api/rec_system_api.dart';
import 'package:flutter_team_project/config/constants.dart';
import 'package:flutter_team_project/models/message_model.dart';
import 'package:flutter_team_project/models/rec_system_model.dart';
import 'package:http/http.dart';

class ChatBuddyScreen extends StatefulWidget {
  const ChatBuddyScreen({Key key}) : super(key: key);

  @override
  State<ChatBuddyScreen> createState() => _ChatBuddyScreenState();
}

class _ChatBuddyScreenState extends State<ChatBuddyScreen> {
  TextEditingController messageController = TextEditingController();

  List<RecSystemModel> _items = [];

  Future<List<RecSystemModel>> readJson() async {
    final String response =
        await rootBundle.loadString('assets/dictionary.json');
    List list = await jsonDecode(response);
    return list.map((e) => RecSystemModel.fromJson(e)).toList();
  }

  read() async {
    List items = await readJson();
    setState(() {
      _items = items;
    });
  }

  @override
  void initState() {
    read();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        leading: IconButton(
            onPressed: (() => Navigator.pop(context)),
            icon: const Icon(
              Icons.arrow_back_outlined,
              color: Colors.black,
            )),
        title: Padding(
          padding: const EdgeInsets.only(left: 15, right: 20),
          child: Row(
            children: [
              const Text(
                'Чат с бадди',
                style: TextStyle(color: Colors.black),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 10, right: 10),
                child: SvgPicture.asset(
                  'assets/light_buddy.svg',
                  fit: BoxFit.cover,
                  height: 15,
                  width: 15,
                ),
              ),
              const Text(
                'онлайн',
                style: TextStyle(color: Colors.black),
              ),
            ],
          ),
        ),
        elevation: 0.0,
        backgroundColor: Colors.white,
      ),
      body: GestureDetector(
        onTap: () {
          unfocus(context);
        },
        child: SafeArea(
          child: Column(
            children: [
              Expanded(
                  child: ListView(
                shrinkWrap: true,
                children: [
                  Padding(
                    padding:
                        const EdgeInsets.only(left: 15, right: 15, top: 15),
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        CircleAvatar(
                          child: Image.network(
                            'https://img.freepik.com/premium-vector/person-avatar-design_24877-38137.jpg?w=2000',
                            fit: BoxFit.cover,
                          ),
                        ),
                        Card(
                            elevation: 0.4,
                            child: Container(
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(15),
                                color: Colors.white,
                              ),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: const [
                                  Padding(
                                    padding: EdgeInsets.all(8.0),
                                    child: Text(
                                      'Приветствую!\nЯ ваш личный помощник!',
                                      style: TextStyle(
                                          color: Colors.black, fontSize: 16),
                                    ),
                                  ),
                                  Padding(
                                    padding: EdgeInsets.all(8.0),
                                    child: Text(
                                      'Можете нажать на кнопку ниже\nили написать свой вопрос в чат',
                                      style: TextStyle(
                                          color: Colors.black, fontSize: 16),
                                    ),
                                  ),
                                ],
                              ),
                            ))
                      ],
                    ),
                  )
                ],
              )),
              SizedBox(
                  height: 30,
                  child: ListView.builder(
                    scrollDirection: Axis.horizontal,
                    physics: const BouncingScrollPhysics(),
                    itemBuilder: (BuildContext context, int index) {
                      if (_items.length == 14) {
                        return RecommendationToUsersInChatWidget(
                            text: _items[index].keyWords.first,
                            endpoint: _items[index].endPoint);
                      }
                      return Container();
                    },
                    itemCount: _items.length,
                  )),
              const Divider(
                thickness: 1.2,
              ),
              Container(
                color: Colors.white,
                padding: const EdgeInsets.only(bottom: 8, left: 8, right: 8),
                child: SafeArea(
                  child: Row(
                    children: <Widget>[
                      GestureDetector(
                        onTap: () async {
                          await OpenChatApi().sendMessage(MessageModel(
                              dialogId: 1,
                              text: 'hello',
                              messageType: 'text',
                              data: 'data',
                              mediaUrl: 'url'));
                        },
                        child: Container(
                            padding: const EdgeInsets.all(8),
                            decoration: BoxDecoration(
                                shape: BoxShape.circle,
                                color: Colors.grey[100]),
                            child: SvgPicture.asset(
                              'assets/pin.svg',
                              color: Colors.black54,
                            )),
                      ),
                      const SizedBox(
                        width: 4,
                      ),
                      Expanded(
                        child: TextField(
                          controller: messageController,
                          textCapitalization: TextCapitalization.sentences,
                          autocorrect: true,
                          enableSuggestions: true,
                          decoration: InputDecoration(
                            focusedBorder: UnderlineInputBorder(
                              borderSide: const BorderSide(width: 0),
                              borderRadius: BorderRadius.circular(10),
                            ),
                            filled: true,
                            fillColor: Colors.grey[100],
                            hintText: 'Напишите свое сообщение сюда',
                            border: UnderlineInputBorder(
                              borderSide: const BorderSide(width: 0),
                              borderRadius: BorderRadius.circular(10),
                            ),
                          ),
                        ),
                      ),
                      const SizedBox(width: 20),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class RecommendationToUsersInChatWidget extends StatelessWidget {
  final String text;
  final String endpoint;
  const RecommendationToUsersInChatWidget({Key key, this.text, this.endpoint})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(
        left: 10,
      ),
      child: GestureDetector(
        onTap: () async {
          await RecSystemApi().request(endpoint);
        },
        child: Container(
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(6),
          ),
          child: Padding(
            padding: const EdgeInsets.all(6),
            child: Center(
                child: Text(
              text,
              style: const TextStyle(color: Colors.orange),
            )),
          ),
        ),
      ),
    );
  }
}
