document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll("[data-table-search]").forEach((input) => {
        const tableId = input.getAttribute("data-table-search");
        const table = document.getElementById(tableId);
        if (!table) {
            return;
        }

        input.addEventListener("input", () => {
            const query = input.value.trim().toLowerCase();
            table.querySelectorAll("tbody tr").forEach((row) => {
                const visible = row.textContent.toLowerCase().includes(query);
                row.style.display = visible ? "" : "none";
            });
        });
    });
});
