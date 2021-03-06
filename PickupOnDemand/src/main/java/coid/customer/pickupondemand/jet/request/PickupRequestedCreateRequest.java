package coid.customer.pickupondemand.jet.request;

import android.content.Context;

import coid.customer.pickupondemand.jet.R;
import coid.customer.pickupondemand.jet.Utility;
import coid.customer.pickupondemand.jet.base.BaseNetworkRequest;
import coid.customer.pickupondemand.jet.model.Pickup;
import coid.customer.pickupondemand.jet.network.RetrofitProvider;
import retrofit2.Call;
import retrofit2.Response;

public class PickupRequestedCreateRequest extends BaseNetworkRequest<Pickup>
{
    private Pickup mPickup;

    public PickupRequestedCreateRequest(Context context, Pickup pickup)
    {
        super(context);
        mPickup = pickup;
    }

    @Override
    public Call<Pickup> getCall()
    {
        return RetrofitProvider.getAuthorizedResourcesService().createRequestedPickup(mPickup.getCode(), mPickup);
    }

    @Override
    protected void onResponseFailedOnUIThread(Response<Pickup> response)
    {
        super.onResponseFailedOnUIThread(response);
        showToast(Utility.Message.getResponseFailedMessage(R.string.pod_create_requested_pickup_failed, response));
    }

    @Override
    protected void onFailedOnUIThread(Exception ex)
    {
        super.onFailedOnUIThread(ex);
        showToast(Utility.Message.getNetworkFailureMessage(R.string.pod_create_requested_pickup_failed, ex));
    }

    @Override
    protected void onTimeOutOnUIThread()
    {
        super.onTimeOutOnUIThread();
        showToast(getString(R.string.pod_request_timed_out));
    }
}
