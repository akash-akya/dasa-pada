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

public class PadaListFragment_ViewBinding implements Unbinder {
  private PadaListFragment target;

  @UiThread
  public PadaListFragment_ViewBinding(PadaListFragment target, View source) {
    this.target = target;

    target.progressBar = Utils.findRequiredViewAsType(source, R.id.vachana_list_progressBar, "field 'progressBar'", ProgressBar.class);
    target.vachanaListContainer = Utils.findRequiredView(source, R.id.vachana_list_container, "field 'vachanaListContainer'");
    target.noDataTv = Utils.findRequiredView(source, R.id.no_data_vachana, "field 'noDataTv'");
  }

  @Override
  @CallSuper
  public void unbind() {
    PadaListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
    target.vachanaListContainer = null;
    target.noDataTv = null;
  }
}
