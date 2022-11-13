import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'open_auth_bloc_event.dart';
part 'open_auth_bloc_state.dart';

class OpenAuthBloc extends Bloc<OpenAuthBlocEvent, OpenAuthBlocState> {
  OpenAuthBloc() : super(OpenAuthBlocInitial()) {
    on<OpenAuthBlocEvent>((event, emit) {
    });
  }
}
