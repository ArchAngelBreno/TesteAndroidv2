package com.desafiozup.presentation.home.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.desafiozup.R;
import com.desafiozup.core.util.FormatUtils;
import com.desafiozup.data.statements.model.Statement;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder> {

    private List<Statement> statements;
    private Context context;

    public StatementAdapter(Context context,List<Statement> statements) {
        this.statements = statements;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.statement_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Statement statement = statements.get(position);

        holder.statementTitle.setText(statement.getTitle());
        holder.statementDate.setText(FormatUtils.convertAmericanDateToBrazilian(statement.getDate()));
        holder.statementDescription.setText(statement.getDesc());
        holder.statementPrice.setText(statement.getValue());

    }

    @Override
    public int getItemCount() {
        return statements.size();
    }

    final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_statement_title)
        TextView statementTitle;

        @BindView(R.id.tv_statement_date)
        TextView statementDate;

        @BindView(R.id.tv_statement_price)
        TextView statementPrice;

        @BindView(R.id.tv_statement_description)
        TextView statementDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
