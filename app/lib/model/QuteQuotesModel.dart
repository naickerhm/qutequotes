class Quote {
  final int id;
  final String text;
  final String name;

  Quote({
    required this.id,
    required this.text,
    required this.name,
  });

  factory Quote.fromJson(Map<String, dynamic> json) {
    return Quote(
      id: json['id'],
      text: json['text'],
      name: json['name'],
    );
  }
}
