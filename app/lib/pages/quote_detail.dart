import 'package:app/model/QuteQuotesModel.dart';
import 'package:flutter/material.dart';

class Detail extends StatelessWidget {
  final Quote quote;
  Detail({required this.quote});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Second Route"),
      ),
      body: Column(
        children: <Widget>[
          Text("Author: ${quote.name}"),
          Text("${quote.text}"),
          Center(
            child: ElevatedButton(
              onPressed: () {
                Navigator.pop(context);
              },
              child: Text('Go back!'),
            ),
          ),
        ],
      ),
    );
  }
}
