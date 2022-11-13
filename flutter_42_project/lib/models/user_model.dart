class UserModel {
  final int userId;
  final String avatar;
  final String surname;
  final String name;
  final String middleName;
  UserModel(
      {this.userId, this.avatar, this.surname, this.name, this.middleName});

  factory UserModel.fromJson(Map<String, dynamic> json) {
    return UserModel(
        userId: json['userId'],
        avatar: json['avatar'],
        surname: json['surname'],
        name: json['name'],
        middleName: json['middleName']);
  }
}
