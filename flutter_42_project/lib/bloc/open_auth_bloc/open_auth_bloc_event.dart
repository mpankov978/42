part of 'open_auth_bloc_bloc.dart';

@immutable
abstract class OpenAuthBlocEvent {}

class OpenAuthLoginEvent extends OpenAuthBlocEvent {}

class OpenAuthRegisterEvent extends OpenAuthBlocEvent{}

class OpenAuthserDataEvent extends OpenAuthBlocEvent{}