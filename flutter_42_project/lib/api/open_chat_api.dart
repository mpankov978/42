import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_team_project/models/message_id_model.dart';
import 'package:flutter_team_project/models/message_model.dart';
import 'package:http/http.dart' as http;

class OpenChatApi {
  Future<MessageIdModel> sendMessage(MessageModel messageModel) async {
    final response =
        await http.post(Uri.parse('https://hack.invest-open.ru/message/send'),
            headers: <String, String>{
              'Accept': 'application/json',
              'Content-Type': 'application/json; charset=UTF-8',
              "Authorization":
                  "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEwMDMzMywibG9naW4iOiJ0aGFpbGFuZCIsInJvbGUiOiJDTElFTlQiLCJpYXQiOjE2NjgyODYyMjZ9.90emh-6qDHWzUJFW0X-5W8ffv7r_y7K1uacsZkEf5Zs"
            },
            body: jsonEncode(<String, dynamic>{
              "dialogId": messageModel.dialogId,
              "text": messageModel.text,
              'messageType': messageModel.messageType,
              'data': messageModel.data,
              'mediaUrl': messageModel.mediaUrl
            }));

    if (response.statusCode == 200) {
      return MessageIdModel.fromJson(jsonDecode(response.body));
    } else {
      debugPrint(response.body);
    }
    return null;
  }
}
