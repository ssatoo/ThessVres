package com.atei.thessvres.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atei.thessvres.PlacesModel;
import com.atei.thessvres.R;
import com.atei.thessvres.interfaces.ObjectToTree;

public class ListAdaptersimple extends BaseAdapter {
	private Context mContext;
	private static LayoutInflater inflater = null;
	List<ObjectToTree> list;
	int IDicon;

	public ListAdaptersimple(Context c, List<ObjectToTree> items, int IDicon) {
		mContext = c;
		this.list = items;
		this.IDicon = IDicon;
		inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public static class ViewHolder {
		public TextView name;
		public ImageView icon;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return getPosition(position);
	}

	public long getItemId(int position) {
		return getPosition(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;
		// position= getPosition(position);
		if (convertView == null) {
			vi = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();

			holder.name = (TextView) vi.findViewById(R.id.label);
			holder.icon = (ImageView) vi.findViewById(R.id.imglist);
			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();

		ObjectToTree item =  list.get(position);

		// UrlImageViewHelper.setUrlDrawable(holder.img, item.getImageUrl(),
		// R.drawable.atromitos);
		holder.icon.setBackgroundResource(IDicon);
		holder.name.setText(((PlacesModel) item).getName() + "\n"
				+ String.format("%.2f", ((PlacesModel) item).getkm()) + "km");

		// GalleryItems item=list.get(position);
		// Log.e("","adapter"+item.getTitle());
		// holder.title.setText(item.getTitle());
		// holder.id.setText(item.getId()+"");
		// final int stub_id=mImageIds[position];
		// holder.image.setImageResource(stub_id);
		// holder.image.setTag(names[position]);

		// ImageView i = new ImageView(mContext);

		/*
		 * i.setImageResource(mImageIds[position]); // i.setLayoutParams(new
		 * Gallery.LayoutParams(150, 150)); i.setId(position);
		 * i.setScaleType(ImageView.ScaleType.FIT_XY);
		 * i.setTag(names[position]);
		 */

		return vi;
	}

	public int checkPosition(int position) {
		return getPosition(position);
	}

	int getPosition(int position) {
		if (position >= list.size()) {
			position = position % list.size();
		}
		return position;
	}

}
