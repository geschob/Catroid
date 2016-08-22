/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2016 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.content.bricks;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Sprite;

import java.util.List;

public class ClearGraphicEffectBrick extends BrickBaseType {
	private static final long serialVersionUID = 1L;

	public ClearGraphicEffectBrick() {
	}

	@Override
	public int getRequiredResources() {
		return NO_RESOURCES;
	}

	@Override
	public View getView(Context context, int brickId, BaseAdapter baseAdapter) {
		if (animationState) {
			return view;
		}
		view = View.inflate(context, R.layout.brick_clear_graphic_effect, null);
		view = getViewWithAlpha(alphaValue);

		setCheckboxView(R.id.brick_clear_graphic_effect_checkbox);
		final Brick brickInstance = this;
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				checked = isChecked;
				adapter.handleCheck(brickInstance, isChecked);
			}
		});

		return view;
	}

	@Override
	public Brick copyBrickForSprite(Sprite sprite) {
		return clone();
	}

	@Override
	public View getViewWithAlpha(int alphaValue) {

		if (view != null) {

			View layout = view.findViewById(R.id.brick_clear_graphic_effect_layout);
			Drawable background = layout.getBackground();
			background.setAlpha(alphaValue);

			TextView clearGraphicEffectLabel = (TextView) view.findViewById(R.id.brick_clear_graphic_effect_label);
			clearGraphicEffectLabel.setTextColor(clearGraphicEffectLabel.getTextColors().withAlpha(alphaValue));

			this.alphaValue = alphaValue;
		}

		return view;
	}

	@Override
	public View getPrototypeView(Context context) {
		return View.inflate(context, R.layout.brick_clear_graphic_effect, null);
	}

	@Override
	public Brick clone() {
		return new ClearGraphicEffectBrick();
	}

	@Override
	public List<SequenceAction> addActionToSequence(Sprite sprite, SequenceAction sequence) {
		sequence.addAction(sprite.getActionFactory().createClearGraphicEffectAction(sprite));
		return null;
	}
}