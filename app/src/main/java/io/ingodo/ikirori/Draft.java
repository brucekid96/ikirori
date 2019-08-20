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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Draft.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Draft#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Draft extends Fragment implements OnDeleteClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    View v;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Event> mEvents;
    EventRepository mRepository;


  private RecyclerView mRecyclerView;
  private adapter mRecyclerAdapter;
  private EventViewModel mModel;
//  private RecyclerViewAdapter madapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Draft() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Draft.
     */
    // TODO: Rename and change types and number of parameters
    public static Draft newInstance(String param1, String param2) {
        Draft fragment = new Draft();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mEvents=new ArrayList<>();
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

      mRecyclerView = (RecyclerView)view.findViewById(R.id.draft_recyclerview);
      mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      mRecyclerAdapter = new adapter(getContext(),mEvents,this);
      mRecyclerView.setAdapter(mRecyclerAdapter);
        // Inflate the layout for this fragment

       mModel= ViewModelProviders.of(this).get(EventViewModel.class);

      mModel.getAllEvents().observe(this, new Observer<List<Event>>() {
        @Override
        public void onChanged(List<Event> events) {
          mRecyclerAdapter.setEvents(events);
        }
      });


        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
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

  /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

