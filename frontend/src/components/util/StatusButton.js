import {Button, Spinner} from "react-bootstrap";

export function StatusButton({
                                 isLoading = false,
                                 isError = false,
                                 children,
                                 ...props
                             }) {
    if (isError) {
        return <Button {...props} disabled>Error</Button>;
    }
    if (isLoading) {
        return <Button {...props} disabled>
            <Spinner
                className="me-2"
                as="span"
                animation="border"
                size="sm"
                role="status"
                aria-hidden="true"
            />
            Loading...
        </Button>
    }
    return <Button {...props}>{children}</Button>;
}
