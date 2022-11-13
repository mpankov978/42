import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_team_project/models/user_model.dart';
import 'package:http/http.dart' as http;

class RecSystemApi {
  final String api = 'http://46.35.244.151:8080/api';
  Future request(String endpoint) async {
    final response = await http.get(
      Uri.parse("$api$endpoint"),
      headers: <String, String>{
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=UTF-8',
        "Authorization":
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEwMDMzMywibG9naW4iOiJ0aGFpbGFuZCIsInJvbGUiOiJDTElFTlQiLCJpYXQiOjE2NjgyODYyMjZ9.90emh-6qDHWzUJFW0X-5W8ffv7r_y7K1uacsZkEf5Zs"
      },
    );

    if (response.statusCode == 200) {
      return jsonDecode(response.body);
    } else {
      debugPrint(response.body);
    }
    return null;
  }
}
