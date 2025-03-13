import React, { useEffect, useState } from 'react';
import { CustomerService } from '../../demo/service/CustomerService';
import { FilterMatchMode, FilterOperator } from 'primereact/api';
import { Button } from 'primereact/button';
import { Calendar } from 'primereact/calendar';
import {
    Column,
    ColumnFilterApplyTemplateOptions,
    ColumnFilterClearTemplateOptions,
    ColumnFilterElementTemplateOptions,
} from 'primereact/column';
import { DataTable, DataTableFilterMeta } from 'primereact/datatable';
import { Dropdown } from 'primereact/dropdown';
import { InputNumber } from 'primereact/inputnumber';
import { InputText } from 'primereact/inputtext';
import { MultiSelect } from 'primereact/multiselect';
import { ProgressBar } from 'primereact/progressbar';
import { Slider } from 'primereact/slider';
import { TriStateCheckbox } from 'primereact/tristatecheckbox';
import { classNames } from 'primereact/utils';

import type { Demo } from '../../layout/types';

const Story: React.FC = () => {
    const [customers, setCustomers] = useState<Demo.Customer[]>([]);
    const [filters, setFilters] = useState<DataTableFilterMeta>({});
    const [loading, setLoading] = useState(true);
    const [globalFilterValue, setGlobalFilterValue] = useState('');

    useEffect(() => {
        CustomerService.getCustomersLarge().then((data) => {
            setCustomers(getCustomers(data));
            setLoading(false);
        });

        initFilters();
    }, []);

    const representatives = [
        { name: 'Amy Elsner', image: 'amyelsner.png' },
        { name: 'Anna Fali', image: 'annafali.png' },
        { name: 'Asiya Javayant', image: 'asiyajavayant.png' },
        { name: 'Bernardo Dominic', image: 'bernardodominic.png' },
        { name: 'Elwin Sharvill', image: 'elwinsharvill.png' },
        { name: 'Ioni Bowcher', image: 'ionibowcher.png' },
        { name: 'Ivan Magalhaes', image: 'ivanmagalhaes.png' },
        { name: 'Onyama Limba', image: 'onyamalimba.png' },
        { name: 'Stephen Shaw', image: 'stephenshaw.png' },
        { name: 'XuXue Feng', image: 'xuxuefeng.png' },
    ];

    const statuses = [
        'unqualified',
        'qualified',
        'new',
        'negotiation',
        'renewal',
        'proposal',
    ];

    const clearFilter = () => {
        initFilters();
    };

    const onGlobalFilterChange1 = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        let _filters = { ...filters };
        (_filters['global'] as any).value = value;

        setFilters(_filters);
        setGlobalFilterValue(value);
    };

    const renderHeader = () => {
        return (
            <div className="flex justify-content-between">
                <Button
                    type="button"
                    icon="pi pi-filter-slash"
                    label="Clear"
                    outlined
                    onClick={clearFilter}
                />
                <span className="p-input-icon-left">
                    <i className="pi pi-search" />
                    <InputText
                        value={globalFilterValue}
                        onChange={onGlobalFilterChange1}
                        placeholder="Keyword Search"
                    />
                </span>
            </div>
        );
    };

    const getCustomers = (data: Demo.Customer[]) => {
        return [...(data || [])].map((d) => {
            d.date = new Date(d.date);
            return d;
        });
    };

    const formatDate = (value: Date) => {
        return value.toLocaleDateString('en-US', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
        });
    };

    const formatCurrency = (value: number) => {
        return value.toLocaleString('en-US', {
            style: 'currency',
            currency: 'USD',
        });
    };

    const initFilters = () => {
        setFilters({
            global: { value: null, matchMode: FilterMatchMode.CONTAINS },
            name: {
                operator: FilterOperator.AND,
                constraints: [
                    { value: null, matchMode: FilterMatchMode.STARTS_WITH },
                ],
            },
            'country.name': {
                operator: FilterOperator.AND,
                constraints: [
                    { value: null, matchMode: FilterMatchMode.STARTS_WITH },
                ],
            },
            representative: { value: null, matchMode: FilterMatchMode.IN },
            date: {
                operator: FilterOperator.AND,
                constraints: [
                    { value: null, matchMode: FilterMatchMode.DATE_IS },
                ],
            },
            balance: {
                operator: FilterOperator.AND,
                constraints: [
                    { value: null, matchMode: FilterMatchMode.EQUALS },
                ],
            },
            status: {
                operator: FilterOperator.OR,
                constraints: [
                    { value: null, matchMode: FilterMatchMode.EQUALS },
                ],
            },
            activity: { value: null, matchMode: FilterMatchMode.BETWEEN },
            verified: { value: null, matchMode: FilterMatchMode.EQUALS },
        });
        setGlobalFilterValue('');
    };

    const countryBodyTemplate = (rowData: Demo.Customer) => {
        return (
            <React.Fragment>
                <img
                    alt="flag"
                    src={`/demo/images/flag/flag_placeholder.png`}
                    className={`flag flag-${rowData.country.code}`}
                    width={30}
                />
                <span style={{ marginLeft: '.5em', verticalAlign: 'middle' }}>
                    {rowData.country.name}
                </span>
            </React.Fragment>
        );
    };

    const filterClearTemplate = (options: ColumnFilterClearTemplateOptions) => {
        return (
            <Button
                type="button"
                icon="pi pi-times"
                onClick={options.filterClearCallback}
                severity="secondary"
            ></Button>
        );
    };

    const filterApplyTemplate = (options: ColumnFilterApplyTemplateOptions) => {
        return (
            <Button
                type="button"
                icon="pi pi-check"
                onClick={options.filterApplyCallback}
                severity="success"
            ></Button>
        );
    };

    const representativeBodyTemplate = (rowData: Demo.Customer) => {
        const representative = rowData.representative;
        return (
            <React.Fragment>
                <img
                    alt={representative.name}
                    src={`/demo/images/avatar/${representative.image}`}
                    onError={(e) =>
                        ((e.target as HTMLImageElement).src =
                            'https://www.primefaces.org/wp-content/uploads/2020/05/placeholder.png')
                    }
                    width={32}
                    style={{ verticalAlign: 'middle' }}
                />
                <span style={{ marginLeft: '.5em', verticalAlign: 'middle' }}>
                    {representative.name}
                </span>
            </React.Fragment>
        );
    };

    const representativeFilterTemplate = (
        options: ColumnFilterElementTemplateOptions
    ) => {
        return (
            <>
                <div className="mb-3 text-bold">Agent Picker</div>
                <MultiSelect
                    value={options.value}
                    options={representatives}
                    itemTemplate={representativesItemTemplate}
                    onChange={(e) => options.filterCallback(e.value)}
                    optionLabel="name"
                    placeholder="Any"
                    className="p-column-filter"
                />
            </>
        );
    };

    const representativesItemTemplate = (option: any) => {
        return (
            <div className="p-multiselect-representative-option">
                <img
                    alt={option.name}
                    src={`/demo/images/avatar/${option.image}`}
                    width={32}
                    style={{ verticalAlign: 'middle' }}
                />
                <span style={{ marginLeft: '.5em', verticalAlign: 'middle' }}>
                    {option.name}
                </span>
            </div>
        );
    };

    const dateBodyTemplate = (rowData: Demo.Customer) => {
        return formatDate(rowData.date);
    };

    const dateFilterTemplate = (
        options: ColumnFilterElementTemplateOptions
    ) => {
        return (
            <Calendar
                value={options.value}
                onChange={(e) => options.filterCallback(e.value, options.index)}
                dateFormat="mm/dd/yy"
                placeholder="mm/dd/yyyy"
                mask="99/99/9999"
            />
        );
    };

    const balanceBodyTemplate = (rowData: Demo.Customer) => {
        return formatCurrency(rowData.balance as number);
    };

    const balanceFilterTemplate = (
        options: ColumnFilterElementTemplateOptions
    ) => {
        return (
            <InputNumber
                value={options.value}
                onChange={(e) => options.filterCallback(e.value, options.index)}
                mode="currency"
                currency="USD"
                locale="en-US"
            />
        );
    };

    const statusBodyTemplate = (rowData: Demo.Customer) => {
        return (
            <span className={`customer-badge status-${rowData.status}`}>
                {rowData.status}
            </span>
        );
    };

    const statusFilterTemplate = (
        options: ColumnFilterElementTemplateOptions
    ) => {
        return (
            <Dropdown
                value={options.value}
                options={statuses}
                onChange={(e) => options.filterCallback(e.value, options.index)}
                itemTemplate={statusItemTemplate}
                placeholder="Select a Status"
                className="p-column-filter"
                showClear
            />
        );
    };

    const statusItemTemplate = (option: any) => {
        return (
            <span className={`customer-badge status-${option}`}>{option}</span>
        );
    };

    const activityBodyTemplate = (rowData: Demo.Customer) => {
        return (
            <ProgressBar
                value={rowData.activity}
                showValue={false}
                style={{ height: '.5rem' }}
            ></ProgressBar>
        );
    };

    const activityFilterTemplate = (
        options: ColumnFilterElementTemplateOptions
    ) => {
        return (
            <React.Fragment>
                <Slider
                    value={options.value}
                    onChange={(e) => options.filterCallback(e.value)}
                    range
                    className="m-3"
                ></Slider>
                <div className="flex align-items-center justify-content-between px-2">
                    <span>{options.value ? options.value[0] : 0}</span>
                    <span>{options.value ? options.value[1] : 100}</span>
                </div>
            </React.Fragment>
        );
    };

    const verifiedBodyTemplate = (rowData: Demo.Customer) => {
        return (
            <i
                className={classNames('pi', {
                    'text-green-500 pi-check-circle': rowData.verified,
                    'text-pink-500 pi-times-circle': !rowData.verified,
                })}
            ></i>
        );
    };

    const verifiedFilterTemplate = (
        options: ColumnFilterElementTemplateOptions
    ) => {
        return (
            <TriStateCheckbox
                value={options.value}
                onChange={(e) => options.filterCallback(e.value)}
            />
        );
    };

    const header1 = renderHeader();

    return (
        <div className="grid">
            <div className="col-12">
                <div className="card">
                    <h5>Filter Menu</h5>
                    <DataTable
                        value={customers}
                        paginator
                        className="p-datatable-gridlines"
                        showGridlines
                        rows={10}
                        dataKey="id"
                        filters={filters}
                        filterDisplay="menu"
                        loading={loading}
                        responsiveLayout="scroll"
                        emptyMessage="No customers found."
                        header={header1}
                    >
                        <Column
                            field="name"
                            header="Name"
                            filter
                            filterPlaceholder="Search by name"
                            style={{ minWidth: '12rem' }}
                        />
                        <Column
                            header="Country"
                            filterField="country.name"
                            style={{ minWidth: '12rem' }}
                            body={countryBodyTemplate}
                            filter
                            filterPlaceholder="Search by country"
                            filterClear={filterClearTemplate}
                            filterApply={filterApplyTemplate}
                        />
                        <Column
                            header="Agent"
                            filterField="representative"
                            showFilterMatchModes={false}
                            filterMenuStyle={{ width: '14rem' }}
                            style={{ minWidth: '14rem' }}
                            body={representativeBodyTemplate}
                            filter
                            filterElement={representativeFilterTemplate}
                        />
                        <Column
                            header="Date"
                            filterField="date"
                            dataType="date"
                            style={{ minWidth: '10rem' }}
                            body={dateBodyTemplate}
                            filter
                            filterElement={dateFilterTemplate}
                        />
                        <Column
                            header="Balance"
                            filterField="balance"
                            dataType="numeric"
                            style={{ minWidth: '10rem' }}
                            body={balanceBodyTemplate}
                            filter
                            filterElement={balanceFilterTemplate}
                        />
                        <Column
                            field="status"
                            header="Status"
                            filterMenuStyle={{ width: '14rem' }}
                            style={{ minWidth: '12rem' }}
                            body={statusBodyTemplate}
                            filter
                            filterElement={statusFilterTemplate}
                        />
                        <Column
                            field="activity"
                            header="Activity"
                            showFilterMatchModes={false}
                            style={{ minWidth: '12rem' }}
                            body={activityBodyTemplate}
                            filter
                            filterElement={activityFilterTemplate}
                        />
                        <Column
                            field="verified"
                            header="Verified"
                            dataType="boolean"
                            bodyClassName="text-center"
                            style={{ minWidth: '8rem' }}
                            body={verifiedBodyTemplate}
                            filter
                            filterElement={verifiedFilterTemplate}
                        />
                    </DataTable>
                </div>
            </div>
        </div>
    );
};

export default Story;
