package com.example.sagar.myapplication.api;

import android.app.Dialog;

import com.example.sagar.myapplication.Err;
import com.example.sagar.myapplication.adapter.BrandAdapter;
import com.example.sagar.myapplication.api.interfaces.ApiBrandInterface;
import com.example.sagar.myapplication.modal.Brand;
import com.example.sagar.myapplication.modal.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandApi{

    private ApiBrandInterface mApiBrandInterface;
    private  static  BrandApi mBrandApi;
    private BrandAdapter  mBrandAdapter;

    public  BrandApi(BrandAdapter brandAdapter){
          mApiBrandInterface = ApiClient.getClient().create(ApiBrandInterface.class);
          this.mBrandAdapter = brandAdapter;
    }

    public  void  addNewBrand(String brand, String  details,String  logo ,final Dialog dialog ){
        mApiBrandInterface.addNewBrand(
             brand , details , logo
        ).enqueue(new Callback<Data>(){
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                    if(response.code() == 200){
                        listBrand(dialog);
                    }
                    else{
                       dialog.dismiss();
                       Err.e("Brand creation failed");
                    }
            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                t.printStackTrace();
                Err.e("Error in adding brand in stacktrace");
            }
        });
    }


    public  void  deleteBrand(String id , final  Dialog dialog){

        mApiBrandInterface
                .deleteBrand(id)
                        .enqueue(new Callback<Data>() {
                            @Override
                            public void onResponse(Call<Data> call, Response<Data> response) {
                                if(response.code()==200){
                                    listBrand(dialog);
                                }
                                else Err.e("Error in deleting employee");
                            }
                            @Override
                            public void onFailure(Call<Data> call, Throwable t){
                                 t.printStackTrace();
                                 Err.e("Error in deleting employee in printStackTrace");
                            }
                        });

    }


    public  void  listBrand(final Dialog dialog){

        mApiBrandInterface.listBrand().enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if(response.code() == 200){
                    mBrandAdapter.addNewItems(response.body());
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure( Call<List<Brand>> call , Throwable t ){
                    t.printStackTrace();
                    Err.e("Failed to load data in printStackTrace");
            }
        });

    }
    public static BrandApi getBrandApi(BrandAdapter mBrandAdapter){

        if(mBrandApi==null)
             mBrandApi = new BrandApi(mBrandAdapter);

        return  mBrandApi;
    }

}
