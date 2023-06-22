package iot.b19060630.mybill.frag_chart;


public class IncomChartFragment extends BaseChartFragment {
    int kind=1;
    @Override
    public void onResume() {
        super.onResume();
        loadData(year,month,kind);
    }

    @Override
    public void setDate(int year, int month) {
        super.setDate(year,month);
        loadData(year,month,kind);

    }
}