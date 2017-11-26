// Generated code from Butter Knife. Do not modify!
package com.akash.dasapada.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.akash.dasapada.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class KathruDetailsFragment_ViewBinding implements Unbinder {
  private KathruDetailsFragment target;

  @UiThread
  public KathruDetailsFragment_ViewBinding(KathruDetailsFragment target, View source) {
    this.target = target;

    target.detailsTextView = Utils.findRequiredViewAsType(source, R.id.tv_kathru_details, "field 'detailsTextView'", TextView.class);
    target.allVachanasButton = Utils.findRequiredViewAsType(source, R.id.btn_vachanas_link, "field 'allVachanasButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    KathruDetailsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.detailsTextView = null;
    target.allVachanasButton = null;
  }
}
