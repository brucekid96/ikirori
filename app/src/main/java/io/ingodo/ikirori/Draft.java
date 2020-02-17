package io.ingodo.ikirori;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.ingodo.ikirori.adapter.OnDeleteClickListener;
import io.ingodo.ikirori.data.Event;
import io.ingodo.ikirori.data.EventRepository;
import io.ingodo.ikirori.data.EventViewModel;
import java.util.List;



public class Draft extends Fragment implements OnDeleteClickListener {
    View v;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Event> mEvents;
    EventRepository mRepository;


  private RecyclerView mRecyclerView;
  private adapter mRecyclerAdapter;
  private EventViewModel mModel;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Draft() {

    }

    public static Draft newInstance(String param1, String param2) {
        Draft fragment = new Draft();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view = inflater.inflate(R.layout.fragment_draft, container, false);

      mRecyclerView =view.findViewById(R.id.draft_recyclerview);
      mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      mRecyclerAdapter = new adapter(getContext(),mEvents,this);
      mRecyclerView.setAdapter(mRecyclerAdapter);

       mModel= ViewModelProviders.of(this).get(EventViewModel.class);
      mModel.getAllEvents().observe(this, new Observer<List<Event>>() {
        @Override
        public void onChanged(List<Event> events) {
          mRecyclerAdapter.setEvents(events);
        }
      });


        return view;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

  @Override
  public void OnDeleteClickListener(Event myEvent) {
    mRepository = new EventRepository(getActivity().getApplication());
    mRepository.delete(myEvent);
  }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}

