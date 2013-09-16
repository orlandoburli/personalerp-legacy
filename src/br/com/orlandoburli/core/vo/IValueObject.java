package br.com.orlandoburli.core.vo;

import java.io.Serializable;

public interface IValueObject extends Serializable, ILog, Cloneable {
	public boolean IsNew();
	public void setNewRecord(boolean isNew);
}