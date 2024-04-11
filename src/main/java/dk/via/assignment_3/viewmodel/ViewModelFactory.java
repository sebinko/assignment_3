package dk.via.assignment_3.viewmodel;

import dk.via.assignment_3.model.Model;

public class ViewModelFactory {
    private final ChatViewModel chatViewModel;
    private final LoginViewModel loginViewModel;

    public ViewModelFactory(Model model) {
        chatViewModel = new ChatViewModel(model);
        loginViewModel = new LoginViewModel(model);
    }

    public ChatViewModel getChatViewModel() {
        return chatViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}
