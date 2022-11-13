class UserAuthModel {
  final int userId;
  final String login;
  final String role;
  final String jwtToken;
  UserAuthModel({this.userId, this.login, this.role, this.jwtToken});

  factory UserAuthModel.fromJson(Map<String, dynamic> json) {
    return UserAuthModel(
        userId: json['userId'],
        login: json['login'],
        role: json['role'],
        jwtToken: json['jwtToken']);
  }
}
