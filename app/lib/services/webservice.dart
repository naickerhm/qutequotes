import 'dart:convert';

// import 'package:change_notifier_demo/models/movie.dart';
import 'package:app/model/QuteQuotesModel.dart';
import 'package:http/http.dart' as http;

class Webservice {
  Future<List<Quote>> getQuotes() async {
    final response =
        await http.get(Uri.parse('http://127.0.0.1:5000/quotes'), headers: {
      "Accept": "application/json",
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    });
    if (response.statusCode == 200) {
      var json = jsonDecode(response.body);
      return json.map((quote) => Quote.fromJson(quote)).toList();
    } else {
      throw Exception("Error downloading quotes..");
    }
  }

  Future<http.Response> postQuote(String author, String text) async {
    final response = await http.post(Uri.parse('http://127.0.0.1:5000/quotes'),
        headers: {
          "Accept": "application/json",
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
        body: jsonEncode(<String, String>{'name': author, 'text': text}));
    if (response.statusCode == 200) {
      return response;
    } else {
      throw Exception("Error posting quotes..");
    }
  }
}
