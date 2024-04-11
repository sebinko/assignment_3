package dk.via.assignment_3.viewmodel;

import dk.via.assignment_3.model.Model;

public class ViewModelFactory {
    private final ChatViewModel chatViewModel;

    public ViewModelFactory(Model model) {
        this.chatViewModel = new ChatViewModel(model);
    }

    public ChatViewModel getChatViewModel() {
        return chatViewModel;
    }
}
