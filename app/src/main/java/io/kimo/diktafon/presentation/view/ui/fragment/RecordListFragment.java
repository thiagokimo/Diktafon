package io.kimo.diktafon.presentation.view.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import io.kimo.diktafon.R;
import io.kimo.diktafon.presentation.model.RecordModel;
import io.kimo.diktafon.presentation.presenter.RecordListPresenter;
import io.kimo.diktafon.presentation.view.RecordListView;

/**
 * Fragment that displays a list of records
 */
public class RecordListFragment extends Fragment implements RecordListView {

    private ListView listView;
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
        listView = (ListView) view.findViewById(R.id.list);
        progressBar = (ProgressWheel) view.findViewById(R.id.progressbar);
        emptyMessage = (TextView) view.findViewById(R.id.empty_feedback);
    }

    private void configureGUI() {

        getActivity().setTitle("Recordings");

        recordListAdapter = new RecordListAdapter(getActivity());
        listView.setAdapter(recordListAdapter);
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
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        listView.setVisibility(View.GONE);
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

    private class RecordListAdapter extends BaseAdapter {
        private List<RecordModel> data;
        private LayoutInflater layoutInflater;

        public RecordListAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        public void setData(List<RecordModel> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        public void clearData() {
            this.data.clear();
            notifyDataSetChanged();
        }


        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public RecordModel getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            RecordModel recordModel = getItem(position);

            convertView = layoutInflater.inflate(R.layout.item_record, parent, false);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView subtitle = (TextView) convertView.findViewById(R.id.subtitle);

            title.setText(recordModel.getTitle());
            subtitle.setText(recordModel.getLenght());

            return convertView;
        }
    }

//    private class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.ViewHolder> {
//
//        private List<RecordModel> data = new ArrayList<>();
//
//        public void setData(List<RecordModel> newData) {
//            this.data = newData;
//            notifyDataSetChanged();
//        }
//
//        public void clearData() {
//            this.data.clear();
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public RecordListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record, viewGroup, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            RecordModel record = data.get(position);
//
//            holder.title.setText(record.getTitle());
//            holder.subtitle.setText(record.getLenght());
//        }
//
//        @Override
//        public int getItemCount() {
//            return data == null ? 0 : data.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//
//            private TextView title, subtitle;
//            private ToggleButton button;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//                title = (TextView) itemView.findViewById(R.id.title);
//                subtitle = (TextView) itemView.findViewById(R.id.subtitle);
//                button = (ToggleButton) itemView.findViewById(R.id.button);
//            }
//        }
//    }
}
