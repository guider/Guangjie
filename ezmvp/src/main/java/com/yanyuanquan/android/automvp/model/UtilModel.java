package com.yanyuanquan.android.automvp.model;

import android.util.Log;
import android.util.LruCache;

import com.yanyuanquan.android.automvp.annotation.Model;

import java.util.HashMap;

/**
 */
public class UtilModel {

    protected static HashMap<Class<?>, EzModel> modelSet = new HashMap<>();

    protected static <M extends EzModel> M getModel(Class<?> clz) {
        M model = (M) modelSet.get(clz);
        if (model == null) {
            model = createModel(clz);
        }
        return model;
    }

    private static <M extends EzModel> M createModel(Class<?> clz) {
        if (clz == null || !EzModel.class.isAssignableFrom(clz)) {
            throw new IllegalArgumentException("  model mast exten baseModel ");
        }
        try {
            M model = (M) clz.newInstance();
            modelSet.put(clz, model);
            return model;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("  model mast exten baseModel ");
    }

    public static <M extends EzModel> M getModelInstance(Class<?> clz) {

        Model model = clz.getAnnotation(Model.class);
        if (model != null) {
            try {
                Class<M> aClass = (Class<M>) model.value();
                return (M) aClass.newInstance();
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(" Presenter 没有Model 注解");
    }

}
