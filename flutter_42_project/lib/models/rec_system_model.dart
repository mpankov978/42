class RecSystemModel {
  final List<dynamic> keyWords;
  final String endPoint;
  RecSystemModel({this.keyWords, this.endPoint});

  factory RecSystemModel.fromJson(Map<String, dynamic> json) {
    return RecSystemModel(keyWords: json['keyWords'], endPoint: json['endpoint']);
  }
}
