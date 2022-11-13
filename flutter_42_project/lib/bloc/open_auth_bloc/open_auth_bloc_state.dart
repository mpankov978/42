part of 'open_auth_bloc_bloc.dart';

@immutable
abstract class OpenAuthBlocState {}

class OpenAuthBlocInitial extends OpenAuthBlocState {}

class OpenAuthLoginState extends OpenAuthBlocState {}

class OpenAuthRegisterState extends OpenAuthBlocState {}

class OpenAuthUserDataState extends OpenAuthBlocState {}

class OpenAuthErrorState extends OpenAuthBlocState {}