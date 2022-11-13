class MessageIdModel {
  final String messageId;
  MessageIdModel({this.messageId});

  factory MessageIdModel.fromJson(Map<String, dynamic> json) {
    return MessageIdModel(messageId: json['messageId']);
  }
}
