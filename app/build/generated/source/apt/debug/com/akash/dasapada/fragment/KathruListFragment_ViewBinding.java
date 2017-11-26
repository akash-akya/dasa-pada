// Generated code from Butter Knife. Do not modify!
package com.akash.dasapada.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.akash.dasapada.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class KathruListFragment_ViewBinding implements Unbinder {
  private KathruListFragment target;

  @UiThread
  public KathruListFragment_ViewBinding(KathruListFragment target, View source) {
    this.target = target;

    target.progressBar = Utils.findRequiredViewAsType(source, R.id.kathru_list_progressBar, "field 'progressBar'", ProgressBar.class);
    target.noDataTextView = Utils.findRequiredView(source, R.id.no_data_kathru, "field 'noDataTextView'");
    target.kathruListContainer = Utils.findRequiredView(source, R.id.kathru_list_container, "field 'kathruListContainer'");
  }

  @Override
  @CallSuper
  public void unbind() {
    KathruListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
    target.noDataTextView = null;
    target.kathruListContainer = null;
  }
}
