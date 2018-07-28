package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimMessage;

public class AdapterActivity extends ArrayAdapter {
    String TAG = "Adapter";
    Context _context;
    List<ClaimMessage> _messageList;

    public AdapterActivity(Context context, int resource, List<ClaimMessage> messageList) {
        super(context,resource,messageList);
        this._messageList=messageList;
        this._context = context;

    }

    @Override
    public ClaimMessage getItem(int i) {
        return _messageList.get(i);
    }

    public View getView( int i, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(_context);

        String text = getItem(i).getMessage();
        String date = getItem(i).getDate();
        String name = getItem(i).getSender();

        if (name.equals("AutoInSure")) {
            convertView = inflater.inflate(R.layout.activity_message_receiver, viewGroup, false);
            //View avatar = (View)convertView.findViewById(R.id.avatar);
            TextView tvName = (TextView) convertView.findViewById(R.id.text_message_name_received);
            TextView tvDate = (TextView) convertView.findViewById(R.id.text_message_time_received);
            TextView tvMessage = (TextView) convertView.findViewById(R.id.text_message_body_received);

            tvName.setText(getItem(i).getSender());
            tvDate.setText(getItem(i).getDate());
            tvMessage.setText(getItem(i).getMessage());

            //GradientDrawable drawable = (GradientDrawable) avatar.getBackground();
            //drawable.setColor(Color.parseColor(ClaimMessages.getRandomColor()));

        }
        else {
            convertView = inflater.inflate(R.layout.activity_message_sent, viewGroup, false);

            //TextView tvName = (TextView) convertView.findViewById(R.id.sender_name);
            TextView tvDate = (TextView) convertView.findViewById(R.id.text_message_time_received);
            TextView tvMessage = (TextView) convertView.findViewById(R.id.text_message_body_received);


            tvDate.setText(getItem(i).getDate());
            tvMessage.setText(getItem(i).getMessage());
        }

        return convertView;
    }
}
