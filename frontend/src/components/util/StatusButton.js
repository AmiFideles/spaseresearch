import {Button, Spinner} from "react-bootstrap";

export function StatusButton({
                                 isLoading = false,
                                 isError = false,
                                 className = "",
                                 variant = "",
                                 type = "",
                                 children
                             }) {
    return (
        <div>{
            isLoading ?
                <Button className={className} variant={variant} disabled>
                    <Spinner
                        className="me-2"
                        as="span"
                        animation="border"
                        size="sm"
                        role="status"
                        aria-hidden="true"
                    />
                    Loading...
                </Button> : isError ?
                    <Button className={className} variant={variant}>Error</Button> :
                    <Button className={className} variant={variant} type={type}>{children}</Button>
        }</div>
    )
}
