class JwtTokenModel {
  final int usedId;
  final String role;

  JwtTokenModel({this.usedId, this.role});

  factory JwtTokenModel.fromJson(Map<String, dynamic> json) {
    return JwtTokenModel(usedId: json['userId'], role: json['role']);
  }
}
