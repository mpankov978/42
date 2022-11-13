import 'dart:io';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_team_project/bloc/open_auth_bloc/open_auth_bloc_bloc.dart';
import 'package:flutter_team_project/config/router.dart';
import 'package:flutter_team_project/screens/service_screens/auth_screen.dart';
import 'package:flutter_team_project/screens/service_screens/tab_controller_screen.dart';
import 'package:flutter_team_project/screens/service_screens/wrapper_screen.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  // final socket = await WebSocket.connect('wss://hack.invest-open.ru/chat',
  //     headers: {'token': 'test'});
  // socket.listen((event) {});
  ByteData data =
      await PlatformAssetBundle().load('assets/ca/lets-encrypt-r3.pem');
  SecurityContext.defaultContext
      .setTrustedCertificatesBytes(data.buffer.asUint8List());

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [
        BlocProvider<OpenAuthBloc>(
          create: ((context) => OpenAuthBloc()),
        )
      ],
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        onGenerateRoute: RouteGenerator.generateRoute,
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: const WrapperScreen(),
      ),
    );
  }
}
