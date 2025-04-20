package com.example.lutemon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lutemon.databinding.FragmentSecondBinding;
// second screen users see when leaving the first screen
public class SecondFragment extends Fragment {
    // connects the code
    private FragmentSecondBinding binding;
    //creates the screen layout
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    // sets up the screen
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    // when button is pressed, takes the user to first screen
        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
    }
    // clears up when leaving the screen
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}