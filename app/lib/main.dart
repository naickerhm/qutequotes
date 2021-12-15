import 'dart:async';
import 'dart:collection';
import 'dart:convert';

import 'package:app/model/QuteQuotesModel.dart';
import 'package:app/pages/quote_detail.dart';
import 'package:app/services/webservice.dart';
import 'package:app/widgets/displayQuotes.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rflutter_alert/rflutter_alert.dart';

// ip address inet 10.40.22.26/24 brd 10.40.22.255

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'QuteQuotes',
      theme: ThemeData(
        // Add the 5 lines from here...
        appBarTheme: const AppBarTheme(
          backgroundColor: Colors.black,
          foregroundColor: Colors.white,
        ),
      ),
      home: QuoteList(),
    );
  }
}

class QuoteList extends StatefulWidget {
  const QuoteList({Key? key}) : super(key: key);
  @override
  _QuoteList createState() => _QuoteList();
}

class _QuoteList extends State<QuoteList> {
  final webservice = Webservice();

  Widget _buildSuggestions() {
    return FutureBuilder(
        future: webservice.getQuotes(),
        builder: (BuildContext context, AsyncSnapshot snapshot) {
          return ListView.builder(
            shrinkWrap: true,
            itemCount: snapshot.data.length,
            padding: const EdgeInsets.all(16.0),
            itemBuilder: (context, i) {
              return Center(
                child: QuoteDisplay(
                  quote: snapshot.data[i],
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) =>
                              Detail(quote: snapshot.data[i])),
                    );
                  },
                ),
              );
            },
          );
        });
  }

  Widget addQuoteButton(BuildContext context) {
    return ElevatedButton(
      onPressed: () => addQuote(context),
      child: Text("Add Quote Here!"),
    );
    // onPressed: doThing(), child: Text("no"));
  }

  addQuote(BuildContext context) {
    final name = TextEditingController();
    final text = TextEditingController();
    Alert(
        context: context,
        title: "Enter fields",
        content: Column(
          children: <Widget>[
            TextField(
              controller: name,
              decoration: const InputDecoration(
                icon: Icon(Icons.add_comment),
                labelText: 'Author',
              ),
            ),
            TextField(
              controller: text,
              keyboardType: TextInputType.multiline,
              textInputAction: TextInputAction.next,
              maxLines: 2,
              maxLength: 100,
              style: const TextStyle(
                color: Colors.black,
              ),
              decoration: const InputDecoration(
                icon: Icon(Icons.text_fields),
                labelText: 'Quote',
              ),
            ),
          ],
        ),
        buttons: [
          DialogButton(
            onPressed: () => showDialog(
              context: context,
              builder: (context) {
                return AlertDialog(
                    content: FutureBuilder(
                        future: webservice.postQuote(name.text, text.text),
                        builder:
                            (BuildContext context, AsyncSnapshot snapshot) {
                          return Center(
                            child: ElevatedButton(
                              onPressed: () {
                                Navigator.push(context,
                                    MaterialPageRoute(builder: (context) {
                                  return const QuoteList();
                                }));
                              },
                              child: const Text('Quote added'),
                            ),
                          );
                        }));
              },
            ),
            child: const Text(
              "Add New Quote",
              style: TextStyle(color: Colors.black, fontSize: 20),
            ),
          )
        ]).show();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Quotes'),
      ),
      // body: _buildSuggestions(),
      body: new Column(
        children: <Widget>[
          Container(
            height: 450,
            child: _buildSuggestions(),
          ),
          addQuoteButton(context),
        ],
      ),
    );
  }
}
