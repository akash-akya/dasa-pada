// Generated code from Butter Knife. Do not modify!
package com.akash.dasapada.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.akash.dasapada.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchFragment_ViewBinding implements Unbinder {
  private SearchFragment target;

  @UiThread
  public SearchFragment_ViewBinding(SearchFragment target, View source) {
    this.target = target;

    target.autoTextView = Utils.findRequiredViewAsType(source, R.id.auto_complete_kathru, "field 'autoTextView'", AutoCompleteTextView.class);
    target.textSearchView = Utils.findRequiredViewAsType(source, R.id.search_bar_text, "field 'textSearchView'", EditText.class);
    target.radioPartial = Utils.findRequiredViewAsType(source, R.id.radio_partial, "field 'radioPartial'", RadioButton.class);
    target.resetButton = Utils.findRequiredViewAsType(source, R.id.reset_button, "field 'resetButton'", Button.class);
    target.searchButton = Utils.findRequiredViewAsType(source, R.id.search_button, "field 'searchButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.autoTextView = null;
    target.textSearchView = null;
    target.radioPartial = null;
    target.resetButton = null;
    target.searchButton = null;
  }
}
