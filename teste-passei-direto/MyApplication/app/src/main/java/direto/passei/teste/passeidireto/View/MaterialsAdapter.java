package direto.passei.teste.passeidireto.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import direto.passei.teste.passeidireto.Model.Material;
import direto.passei.teste.passeidireto.R;
import direto.passei.teste.passeidireto.Service.DataService;
import direto.passei.teste.passeidireto.Util.ActivityUtil;

/**
 * Created by lunid on 25/01/2018.
 */

public class MaterialsAdapter extends BaseAdapter {

    private List<Material> materials;
    private LayoutInflater inflater;
    private ItemViewHolder itemViewHolder;
    private Context context;

    public MaterialsAdapter(Context context, List<Material> materials) {
        this.materials = materials;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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

        setMaterialFavorite(itemViewHolder, (MainActivity_) context, position);

        favoriteClick(itemViewHolder, (MainActivity_) context, position);

        return itemView;
    }

    private void favoriteClick(final ItemViewHolder itemViewHolder,
                               final MainActivity_ context,
                               final Integer position) {
        itemViewHolder.favoriteImageView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (context.isMaterialFavorite(getItem(position))) {
                            context.removeFavoriteMaterial(getItem(position));
                            changeFavoriteIcon(itemViewHolder,
                                    context.getResources()
                                            .getDrawable(R.drawable.ic_empty_star_material_design));
                        } else {
                            context.setFavoriteMaterial(getItem(position));
                            changeFavoriteIcon(itemViewHolder,
                                    context.getResources()
                                            .getDrawable(R.drawable.ic_star_material_design));
                        }
                    }
                });
    }

    public void setMaterialFavorite(ItemViewHolder itemViewHolder,
                                    MainActivity_ context,
                                    int position) {
        if (context.isMaterialFavorite(getItem(position)))
            changeFavoriteIcon(itemViewHolder,
                    context.getResources()
                            .getDrawable(R.drawable.ic_star_material_design));
        else
            changeFavoriteIcon(itemViewHolder,
                    context.getResources()
                            .getDrawable(R.drawable.ic_empty_star_material_design));
    }

    public void changeFavoriteIcon(ItemViewHolder itemViewHolder, Drawable drawable) {
        itemViewHolder.favoriteImageView.setImageDrawable(drawable);
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

        TextView contentNameTextView,
                subjectNameTextView,
                universityNameTextView;

        ImageView favoriteImageView;

        private ItemViewHolder(View view) {
            contentNameTextView =
                    (TextView) view.findViewById(R.id.contentNameTextView);
            subjectNameTextView =
                    (TextView) view.findViewById(R.id.subjectNameTextView);
            universityNameTextView =
                    (TextView) view.findViewById(R.id.universityNameTextView);
            favoriteImageView =
                    (ImageView) view.findViewById(R.id.favoriteImageView);
        }
    }
}
