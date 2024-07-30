import clsx from "clsx";
import styles from "./Button.module.scss";

const Button = (props) => {
	return (
		<button className={clsx(styles.button)}>
			<div
				className={clsx(styles.textButton, {
					[styles.default]: props?.default,
					[styles.primary]: props?.primary,
					[styles.outline]: props?.outline,
				})}
			>
				{props.children}
			</div>
		</button>
	);
};

const ActionButton = (props) => {
	return (
		<div className={clsx(styles.actionButton)}>
			<div className={clsx(styles.iconWrapper)}>{props.children}</div>
			<div className={clsx(styles.countAction)}>{props.count}</div>
		</div>
	);
};

export default Button;
export { ActionButton };
