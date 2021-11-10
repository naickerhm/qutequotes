import 'package:http/http.dart' as http;

class QuteQoutesController {
  Future<Map<String, String>> GetQuotes() async {
    final response = await http
        .get(Uri.parse('https://miniature-glockenspiel.glitch.me/movies'));
    if (response.statusCode == 200) {
      return getQuotesFromJson(response.body);
    } else {
      throw Exception("Error downloading movies..");
    }
  }

  Map<String, String> getQuotesFromJson(String body) {
    Map<String, String> newList = {"poop": "poopquote", "2ndpoop": "two times"};
    return newList;
  }
}
