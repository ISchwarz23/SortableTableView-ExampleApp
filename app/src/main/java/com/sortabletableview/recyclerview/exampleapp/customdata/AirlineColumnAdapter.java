package com.sortabletableview.recyclerview.exampleapp.customdata;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sortabletableview.recyclerview.TableDataColumnAdapterDelegator;
import com.sortabletableview.recyclerview.exampleapp.data.Flight;

public final class AirlineColumnAdapter extends TableDataColumnAdapterDelegator.TableDataColumnAdapter<Flight, AirlineColumnAdapter.ImageViewHolder> {

    @Override
    public ImageViewHolder onCreateColumnCellViewHolder(final ViewGroup parent, final int viewType) {
        final ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        return new ImageViewHolder(imageView);
    }

    @Override
    public void onBindColumnCellViewHolder(final ImageViewHolder viewHolder, final int rowIndex) {
        final Flight flight = getRowData(rowIndex);
        viewHolder.setImageResource(flight.getAirline().getLogoImageRes());
    }

    static final class ImageViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        private ImageViewHolder(final ImageView imageView) {
            super(imageView);
            this.imageView = imageView;
        }

        private void setImageResource(final int imageRes) {
            imageView.setImageResource(imageRes);
        }
    }
}