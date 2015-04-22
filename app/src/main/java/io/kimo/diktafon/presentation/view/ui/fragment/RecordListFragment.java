package io.kimo.diktafon.presentation.view.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import io.kimo.diktafon.R;
import io.kimo.diktafon.presentation.model.RecordModel;
import io.kimo.diktafon.presentation.presenter.RecordListPresenter;
import io.kimo.diktafon.presentation.view.RecordListView;

/**
 * Fragment that displays a list of records
 */
public class RecordListFragment extends Fragment implements RecordListView {

    private RecyclerView recyclerView;
    private ProgressWheel progressBar;
    private TextView emptyMessage;
    private RecordListAdapter recordListAdapter;

    private RecordListPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mapGUI(view);
        configureGUI();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new RecordListPresenter(getActivity(), this);
        presenter.assembleView();
    }

    private void mapGUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progressBar = (ProgressWheel) view.findViewById(R.id.progressbar);
        emptyMessage = (TextView) view.findViewById(R.id.empty_feedback);
    }

    private void configureGUI() {

        getActivity().setTitle("Recordings");

        recordListAdapter = new RecordListAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recordListAdapter);
    }

    @Override
    public void renderRecordList(List<RecordModel> records) {
        recordListAdapter.setData(records);
    }

    @Override
    public void clearRecordList() {
        recordListAdapter.clearData();
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty(String feedback) {
        emptyMessage.setText(feedback);
        emptyMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        emptyMessage.setVisibility(View.GONE);
    }

    private class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.ViewHolder> {

        private List<RecordModel> data = new ArrayList<>();

        public void setData(List<RecordModel> newData) {
            this.data = newData;
            notifyDataSetChanged();
        }

        public void clearData() {
            this.data.clear();
            notifyDataSetChanged();
        }

        @Override
        public RecordListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            RecordModel record = data.get(position);

            holder.title.setText(record.getTitle());
            holder.subtitle.setText(record.getLenght());
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView title, subtitle;
            private ToggleButton button;

            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.title);
                subtitle = (TextView) itemView.findViewById(R.id.subtitle);
                button = (ToggleButton) itemView.findViewById(R.id.button);
            }
        }
    }
}
