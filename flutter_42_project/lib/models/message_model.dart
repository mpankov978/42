class MessageModel {
  final int dialogId;
  final String text;
  final String messageType;
  final String data;
  final String mediaUrl;

  MessageModel(
      {this.dialogId, this.text, this.messageType, this.data, this.mediaUrl});

  factory MessageModel.fromJson(Map<String, dynamic> json) {
    return MessageModel(
        dialogId: json['dialogId'],
        text: json['text'],
        messageType: json['messageType'],
        data: json['data'],
        mediaUrl: json['mediaUrl']);
  }
}
