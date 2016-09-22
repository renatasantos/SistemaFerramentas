package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.renata.sistemaferramentas.R;

import java.util.ArrayList;

/**
 * Created by renata on 22/09/16.
 */

public class MyCustomRowBaseAdapter extends BaseAdapter {

    private ArrayList<DadosFerramenta> dadosFerramentaArrayList;
    private LayoutInflater mInflater;

    public MyCustomRowBaseAdapter(Context context, ArrayList<DadosFerramenta> dadosFerramentas){
        dadosFerramentaArrayList = dadosFerramentas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dadosFerramentaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dadosFerramentaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            convertView = mInflater.inflate(R.layout.custom_row_listview, null);
            holder = new ViewHolder();

            holder.txtNomeFerramenta = (TextView)convertView.findViewById(R.id.txtNomeFerramenta);
            holder.txtFabricante = (TextView)convertView.findViewById(R.id.txtFabricante);
            holder.txtReferencia = (TextView)convertView.findViewById(R.id.txtReferencia);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.txtNomeFerramenta.setText(dadosFerramentaArrayList.get(position).nomeFerramenta);
        holder.txtFabricante.setText(dadosFerramentaArrayList.get(position).fabricante);
        holder.txtReferencia.setText(dadosFerramentaArrayList.get(position).referencia);

        return convertView;
    }

    static class ViewHolder {
        TextView txtNomeFerramenta;
        TextView txtFabricante;
        TextView txtReferencia;
    }
}
