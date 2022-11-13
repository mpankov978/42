import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_team_project/models/jwt_token_model.dart';
import 'package:flutter_team_project/models/user_auth_model.dart';
import 'package:flutter_team_project/models/user_model.dart';
import 'package:http/http.dart' as http;

class OpenUserApi {
  Future<UserAuthModel> authenticate() async {
    final response =
        await http.post(Uri.parse("https://hack.invest-open.ru/auth"),
            headers: <String, String>{
              "Content-Type": "application/json",
            },
            body: jsonEncode(<String, Object>{
              "login": "thailand",
              "password":
                  "845e479731ed9cce0721f98baa718c2d7ed42e7ccb2474b78c28616d6270763d"
            }));

    if (response.statusCode == 200) {
      return UserAuthModel.fromJson(jsonDecode(response.body));
    } else {
      debugPrint(response.body);
    }
    return null;
  }

  Future<JwtTokenModel> verifyToken(String jwtToken) async {
    final response =
        await http.post(Uri.parse("https://hack.invest-open.ru/jwt/verify"),
            headers: <String, String>{
              'Accept': 'application/json',
              'Content-Type': 'application/json; charset=UTF-8',
                "Authorization":
              "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEwMDMzMywibG9naW4iOiJ0aGFpbGFuZCIsInJvbGUiOiJDTElFTlQiLCJpYXQiOjE2NjgyODYyMjZ9.90emh-6qDHWzUJFW0X-5W8ffv7r_y7K1uacsZkEf5Zs"
            },
            body: jsonEncode(<String, dynamic>{
              "jwt": jwtToken,
            }));

    if (response.statusCode == 200) {
      return JwtTokenModel.fromJson(jsonDecode(response.body));
    } else {
      debugPrint(response.body);
    }
    return null;
  }

  Future<UserModel> getUser(int userId) async {
    final response = await http.get(
      Uri.parse("https://hack.invest-open.ru/user/info?userId=$userId"),
      headers: <String, String>{
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=UTF-8',
        "Authorization":
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEwMDMzMywibG9naW4iOiJ0aGFpbGFuZCIsInJvbGUiOiJDTElFTlQiLCJpYXQiOjE2NjgyODYyMjZ9.90emh-6qDHWzUJFW0X-5W8ffv7r_y7K1uacsZkEf5Zs"
      },
    );

    if (response.statusCode == 200) {
      return UserModel.fromJson(jsonDecode(response.body));
    } else {
      debugPrint(response.body);
    }
    return null;
  }

  Future<UserModel> changeInfoUser(
      String avatar, String surname, String name, String middleName) async {
    final response = await http.post(
        Uri.parse("https://hack.invest-open.ru/user/info"),
        headers: <String, String>{
          'Accept': 'application/json',
          'Content-Type': 'application/json; charset=UTF-8',
          "Authorization":
              "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEwMDMzMywibG9naW4iOiJ0aGFpbGFuZCIsInJvbGUiOiJDTElFTlQiLCJpYXQiOjE2NjgyODYyMjZ9.90emh-6qDHWzUJFW0X-5W8ffv7r_y7K1uacsZkEf5Zs"
        },
        body: jsonEncode(<String, dynamic>{
          "avatar": avatar,
          "surname": surname,
          "name": name,
          "middleName": middleName
        }));

    if (response.statusCode == 200) {
      return UserModel.fromJson(jsonDecode(response.body));
    } else {
      debugPrint(response.body);
    }
    return null;
  }
}
