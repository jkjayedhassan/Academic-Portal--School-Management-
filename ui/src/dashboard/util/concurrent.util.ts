export const debounce = <T>(fn: () => T, delay: number) => {
    let timer: any = null;
    return function () {
        if (timer) {
            clearTimeout(timer);
        }
        timer = setTimeout(fn, delay);
    };
}