package br.com.orlandoburli.core.vo;

import java.io.Serializable;

/**
 * Interface que representa todos os VO's.
 * @author orlandoburli
 */
public interface IValueObject extends Serializable, ILog, Cloneable {
	public boolean IsNew();
	public void setNewRecord(boolean isNew);
}