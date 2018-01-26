package direto.passei.teste.passeidireto.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import direto.passei.teste.passeidireto.Model.Material;
import direto.passei.teste.passeidireto.R;

/**
 * Created by lunid on 25/01/2018.
 */

public class MaterialsAdapter extends BaseAdapter {

    private List<Material> materials;
    private LayoutInflater inflater;
    private ItemViewHolder itemViewHolder;

    public MaterialsAdapter(Context context, List<Material> materials) {
        this.materials = materials;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return materials.size();
    }

    @Override
    public Material getItem(int position) {
        return materials.get(position);
    }

    @Override
    public long getItemId(int position) {
        return materials.get(position).getId();
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {

        itemView = setItemViewHolder(itemView, parent);

        setItemView(position);

        return itemView;
    }

    private View setItemViewHolder(View itemView, ViewGroup parent) {
        if (itemView == null) {
            itemView = inflater.inflate(R.layout.material_listview_item, parent, false);
            itemViewHolder = new MaterialsAdapter.ItemViewHolder(itemView);
            itemView.setTag(itemViewHolder);
        } else
            itemViewHolder = (MaterialsAdapter.ItemViewHolder) itemView.getTag();
        return itemView;
    }

    private void setItemView(int position) {
        if (materials.size() > 0) {
            setMaterialFields(getItem(position));
        } else
            setNoMaterialText();
    }

    private void setMaterialFields(Material material) {
        itemViewHolder.contentNameTextView.setText(
                "Nome: " + material.getName());
        itemViewHolder.subjectNameTextView.setText(
                "Assunto: " + material.getSubjectName());
        itemViewHolder.universityNameTextView.setText(
                "Universidade: " + material.getUniversityName());
    }

    private void setNoMaterialText() {
        itemViewHolder.subjectNameTextView.setText(R.string.no_material_found);
    }


    private static class ItemViewHolder {

        TextView contentNameTextView;
        TextView subjectNameTextView;
        TextView universityNameTextView;

        private ItemViewHolder(View view) {
            contentNameTextView =
                    (TextView) view.findViewById(R.id.contentNameTextView);
            subjectNameTextView =
                    (TextView) view.findViewById(R.id.subjectNameTextView);
            universityNameTextView =
                    (TextView) view.findViewById(R.id.universityNameTextView);
        }
    }
}
