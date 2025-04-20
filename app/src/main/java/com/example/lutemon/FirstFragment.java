package com.example.lutemon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lutemon.databinding.FragmentFirstBinding;
// the first screen in the app that shows when you open it
public class FirstFragment extends Fragment {

    // conects the layout file
    private FragmentFirstBinding binding;
    // creates the screen layout
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // set up screen design for xml's
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    // sets up button when screen is created
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(v ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );
    }
    // cleans up when leaving the screen
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}